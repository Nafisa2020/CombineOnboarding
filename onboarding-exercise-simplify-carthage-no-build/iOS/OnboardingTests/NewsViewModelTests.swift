// Copyright © 2020 Itty Bitty Apps. All rights reserved.

//import Domain
//@testable import Onboarding
//import RxSwift
//import RxTest
//import XCTest
//
//class NewsViewModelTests: XCTestCase {
//  private var viewModel: NewsViewModel!
//  private var testScheduler: TestScheduler!
//  private var disposeBag: DisposeBag!
//
//  override func setUp() {
//    super.setUp()
//
//    let mockService = MockService()
//    viewModel = NewsViewModel(service: mockService)
//    testScheduler = TestScheduler(initialClock: 0)
//    disposeBag = DisposeBag()
//  }
//
//  func testInitialStateIsLoading() {
//    let observer = testScheduler.createObserver(NewsViewModel.State.self)
//    viewModel.states.drive(observer).disposed(by: disposeBag)
//    testScheduler.start()
//
//    XCTAssertEqual(observer.events, [.next(0, .init(title: "Loading...", items: []))])
//  }
//
//  func testLoadingItems() {
//    let observer = testScheduler.createObserver(NewsViewModel.State.self)
//    viewModel.states.drive(observer).disposed(by: disposeBag)
//    testScheduler.scheduleAt(10, action: viewModel.loadNews)
//    testScheduler.start()
//
//    XCTAssertEqual(
//      observer.events,
//      [
//        .next(0, .init(title: "Loading...", items: [])),
//        .next(10, .init(title: "Hacker News", items: [NewsItem(item: MockService.exampleItem)]))
//      ]
//    )
//  }
//}
//
//private class MockService: HackerNewsServiceProtocol {
//  func fetchTopItemIds() -> Single<[Int]> { .just([]) }
//
//  static let exampleItem = Item(
//    id: 8863,
//    type: .story,
//    by: "dhouston",
//    time: Date(timeIntervalSince1970: 1175714200),
//    kids: [
//      8952, 9224, 8917, 8884, 8887, 8943, 8869, 8958, 9005, 9671, 8940, 9067, 8908, 9055, 8865,
//      8881, 8872, 8873, 8955, 10403, 8903, 8928, 9125, 8998, 8901, 8902, 8907, 8894, 8878, 8870,
//      8980, 8934, 8876
//    ],
//    url: URL(string: "http://www.getdropbox.com/u/2/screencast.html")!, // swiftlint:disable:this force_unwrapping
//    score: 111,
//    title: "My YC app: Dropbox - Throw away your USB drive",
//    descendants: 71
//  )
//
//  func fetchItems(forIDs ids: [Int]) -> Single<[Item]> {
//    .just([
//      MockService.exampleItem
//    ])
//  }
//}
