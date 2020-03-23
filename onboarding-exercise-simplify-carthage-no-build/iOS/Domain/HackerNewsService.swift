//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Combine
import Foundation


public protocol HackerNewsServiceProtocol {
  func fetchTopItemIds() -> AnyPublisher<[Int], Error>
  func fetchItems(forIDs ids: [Int]) -> AnyPublisher<[Item], Error>
}

public final class HackerNewsService: HackerNewsServiceProtocol {
  private let dataProvider: HackerNewsDataProviding
  private let decoder: JSONDecoder = {
    let decoder = JSONDecoder()
    decoder.dateDecodingStrategy = .secondsSince1970
    return decoder
  }()

  public convenience init() {
    self.init(dataProvider: HackerNewsDataProvider())
  }

  init(dataProvider: HackerNewsDataProviding) {
    self.dataProvider = dataProvider
  }

  public func fetchTopItemIds() -> AnyPublisher<[Int], Error> {
    dataProvider.topItemIds()
      .decode(type: [Int].self, decoder: decoder)
      .print()
      .eraseToAnyPublisher()
  }

  public func fetchItems(forIDs ids: [Int]) -> AnyPublisher<[Item], Error> {
    let items = ids.map { [decoder] in
      dataProvider.item(for: $0)
        .decode(type: Item.self, decoder: decoder)
        .print()
        .eraseToAnyPublisher()
    }

    return Publishers.ConcatenateMany(items).collect().eraseToAnyPublisher()
  }
}

extension Publishers {
  public struct ConcatenateMany<Upstream>: Publisher where Upstream: Publisher {
    public typealias Output = Upstream.Output
    public typealias Failure = Upstream.Failure
    public let publishers: [Upstream]
    private let concatenatePublisher: AnyPublisher<Upstream.Output, Upstream.Failure>

    public init(_ upstream: Upstream...) {
      self.init(upstream)
    }
    public init<S>(_ upstream: S) where Upstream == S.Element, S: Swift.Sequence {
      publishers = Array(upstream)
      let partialResult = Empty<Upstream.Output, Upstream.Failure>().eraseToAnyPublisher()
      concatenatePublisher = publishers
        .reduce(partialResult) { Concatenate(prefix: $0, suffix: $1).eraseToAnyPublisher() }
    }
    public func receive<S>(subscriber: S)
      where
      S: Subscriber,
      ConcatenateMany.Failure == S.Failure,
      ConcatenateMany.Output == S.Input {
      concatenatePublisher.receive(subscriber: subscriber)
    }
  }
}
