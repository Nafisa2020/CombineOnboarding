//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Domain

class CommentsViewModel {
  struct State {
    let title: String
  }

  @Published var state: State

  private let item: Item
  private let service: HackerNewsServiceProtocol

  init(item: Item, service: HackerNewsServiceProtocol) {
    self.item = item
    self.service = service
    self.state = (State(title: item.title ?? ""))
  }
}
