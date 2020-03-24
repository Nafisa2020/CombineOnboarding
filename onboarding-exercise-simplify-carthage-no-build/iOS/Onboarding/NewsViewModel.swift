//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain

class NewsViewModel {
  var commands: AnyPublisher<Command, Never>!
  var states: AnyPublisher<State, Never>!

  private let service: HackerNewsServiceProtocol
  private let itemStateSubject = CurrentValueSubject<ItemState, Never>(.loading)
  private let commandSubject = PassthroughSubject<Command, Never>()

  private var disposables = Set<AnyCancellable>()

  init(service: HackerNewsServiceProtocol) {
    self.service = service

    self.states = itemStateSubject
      .map { itemState in
        return State(title: itemState.title, items: itemState.items.map(NewsItem.init))
      }
     .eraseToAnyPublisher()

    self.commands = commandSubject
      .eraseToAnyPublisher()
  }

  // MARK: - Events / Actions

  func loadNews() {
    service.fetchTopItemIds()
      .flatMap { [service] ids in service.fetchItems(forIDs: Array(ids.prefix(20))) }

      .sink(
        receiveCompletion: { [weak self] value in
          switch value {
          case .failure:
            self?.itemStateSubject.send(.error)

          case .finished:
            break
          }
        },
        receiveValue: {[weak self] in
          self?.itemStateSubject.send(.loaded($0))
        })
      .store(in: &disposables)
  }

  func selectItem(at row: Int) {
    let item = itemStateSubject.value.items[row]
    let viewModel = CommentsViewModel(item: item, service: service)
    commandSubject.send(.showComments(viewModel))
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
