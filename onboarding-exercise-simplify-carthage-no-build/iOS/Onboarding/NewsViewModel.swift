//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain

class NewsViewModel {
  @Published var states: State!
  @Published var commands: Command!

  private let service: HackerNewsServiceProtocol
  private let itemStateRelay = CurrentValueSubject<ItemState, Never>(.loading)

  private var disposables = Set<AnyCancellable>()

  init(service: HackerNewsServiceProtocol) {
    self.service = service

    self.states = State(title: itemStateRelay.value.title, items: itemStateRelay.value.items.map(NewsItem.init))
  }

  // MARK: - Events / Actions

  func loadNews() {
    service.fetchTopItemIds()
      .flatMap { [service] ids in service.fetchItems(forIDs: Array(ids.prefix(20))) }

      .sink(
        receiveCompletion: { [weak self] value in
          switch value {
          case .failure:
            self?.itemStateRelay.send(.error)

          case .finished:
            break
          }
        },
        receiveValue: {[weak self] in
          self?.itemStateRelay.send(.loaded($0))

          guard let title = self?.itemStateRelay.value.title ,
            let items = self?.itemStateRelay.value.items else {
              return
          }

          self?.states = State(title: title, items: items.map(NewsItem.init))
        })
      .store(in: &disposables)
  }

  func selectItem(at row: Int) {
    let item = itemStateRelay.value.items[row]
    let viewModel = CommentsViewModel(item: item, service: service)
    self.commands = .showComments(viewModel)
  }

  // MARK: - Nested Types

  struct State: Equatable {
    let title: String
    let items: [NewsItem]
  }

  enum Command {
    case showComments(CommentsViewModel)
  }

  private enum ItemState {
    case loading
    case loaded([Item])
    case error

    var title: String {
      switch self {
      case .loading: return "Loading..."
      case .loaded: return "Hacker News"
      case .error: return "Error"
      }
    }

    var items: [Item] {
      switch self {
      case .loading, .error: return []
      case .loaded(let items): return items
      }
    }
  }
}
