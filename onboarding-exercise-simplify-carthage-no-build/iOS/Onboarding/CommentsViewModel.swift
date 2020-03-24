//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain

class CommentsViewModel {
  struct State {
    let title: String
  }

  var state: AnyPublisher<State, Never>!
  private let stateSubject: CurrentValueSubject<State, Never>!
  private let item: Item
  private let service: HackerNewsServiceProtocol

  init(item: Item, service: HackerNewsServiceProtocol) {
    self.item = item
    self.service = service
    stateSubject = CurrentValueSubject<State, Never>(State(title: item.title ?? ""))

    self.state = stateSubject
      .print()
    .eraseToAnyPublisher()
  }
}
