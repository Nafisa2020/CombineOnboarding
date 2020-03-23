//  Copyright © 2018 Itty Bitty Apps. All rights reserved.

import Domain
import Foundation

struct NewsItem: Hashable {
  let title: String
  let subTitle: String
  let showsDisclosure: Bool

  private static let relativeDateFormatter = RelativeDateTimeFormatter()

  init(item: Item) {
    title = item.title ?? ""

    let now = Date()
    let relativeDateFormatter = NewsItem.relativeDateFormatter

    let scoreDescription = item.score.map { "\($0) points" }
    let byDescription = item.by.map { "by \($0)" }
    let timeAgo = item.time.map { relativeDateFormatter.localizedString(for: $0, relativeTo: now) }
    let commentsDescription = item.descendants.map { "| \($0) comments" }

    subTitle = [scoreDescription, byDescription, timeAgo, commentsDescription]
      .compactMap({ $0 })
      .joined(separator: " ")

    showsDisclosure = (item.descendants ?? 0) > 0
  }
}
