//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain

class CommentsViewModel {
  struct State {
    let title: String
  }

  @Published var state: State

  private let item: Item
  private let stateRelay: CurrentValueSubject<State, Never>
  private let service: HackerNewsServiceProtocol

  init(item: Item, service: HackerNewsServiceProtocol) {
    self.item = item
    self.service = service
    self.stateRelay = CurrentValueSubject<State, Never> (State(title: item.title ?? ""))
    self.state = self.stateRelay.value
  }
}
