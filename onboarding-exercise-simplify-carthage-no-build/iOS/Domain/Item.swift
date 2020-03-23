//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Foundation

// Definition: https://github.com/HackerNews/API
public struct Item: Decodable {
  public let id: Int
  public let deleted: Bool? // swiftlint:disable:this discouraged_optional_boolean
  public let type: ItemType?
  public let by: String?
  public let time: Date?
  public let text: String?
  public let dead: Bool? // swiftlint:disable:this discouraged_optional_boolean
  public let parent: Int?
  public let poll: Int?
  public let kids: [Int]?
  public let url: URL?
  public let score: Int?
  public let title: String?
  public let parts: [Int]?
  public let descendants: Int?

  public enum ItemType: String, Decodable {
    case job, story, comment, poll, pollopt
  }

  public init(
    id: Int,
    deleted: Bool? = nil, // swiftlint:disable:this discouraged_optional_boolean
    type: ItemType? = nil,
    by: String? = nil,
    time: Date? = nil,
    text: String? = nil,
    dead: Bool? = nil, // swiftlint:disable:this discouraged_optional_boolean
    parent: Int? = nil,
    poll: Int? = nil,
    kids: [Int]? = nil,
    url: URL? = nil,
    score: Int? = nil,
    title: String? = nil,
    parts: [Int]? = nil,
    descendants: Int? = nil
  ) {
    self.id = id
    self.deleted = deleted
    self.type = type
    self.by = by
    self.time = time
    self.text = text
    self.dead = dead
    self.parent = parent
    self.poll = poll
    self.kids = kids
    self.url = url
    self.score = score
    self.title = title
    self.parts = parts
    self.descendants = descendants
  }
}
