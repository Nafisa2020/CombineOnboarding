//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Foundation


protocol HackerNewsDataProviding {
  typealias ItemId = Int

  func topItemIds() -> AnyPublisher<Data, Error>
  func item(for id: ItemId) -> AnyPublisher<Data, Error>
  func url(for id: ItemId) -> String
}

final class HackerNewsDataProvider: HackerNewsDataProviding {
  private let session = URLSession(configuration: .default)
  private let baseUrl = "https://hacker-news.firebaseio.com"
  private var topStoriesUrl: String { return "\(self.baseUrl)/v0/topstories.json" }
  private var itemUrlFormat: String { return "\(self.baseUrl)/v0/item/%u.json" }

  func topItemIds() -> AnyPublisher<Data, Error> {
    self.dataTaskObservable(urlString: topStoriesUrl)
  }

  func item(for id: ItemId) -> AnyPublisher<Data, Error> {
    self.dataTaskObservable(urlString: url(for: id))
  }

  func url(for id: ItemId) -> String {
    String(format: itemUrlFormat, id)
  }
}

private extension HackerNewsDataProvider {
  func dataTaskObservable(urlString: String) -> AnyPublisher<Data, Error> {

    guard let url = URL(string: urlString) else {
      return Fail(error: URLError(.badURL))
        .eraseToAnyPublisher()
    }

    return session.dataTaskPublisher(for: URLRequest(url:url))
      .tryMap() { element -> Data in
        guard let httpResponse = element.response as? HTTPURLResponse,
          httpResponse.statusCode == 200 else {
            throw URLError(.badServerResponse)
        }
        return element.data
    }
    .eraseToAnyPublisher()
  }
}
