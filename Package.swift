// swift-tools-version: 5.7

import PackageDescription

let version = "1.0.0"

let package = Package(
    name: "CurrencyExchangeKMP",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "CurrencyExchangeKMP",
            targets: ["CurrencyExchangeKMP"]
        )
    ],
    dependencies: [
            .package(url: "https://github.com/Mindera/Android-KMP-Template.git", .branch("iOS_Framework_Debug")),
            .package(url: "https://github.com/Mindera/Android-KMP-Template.git", .branch("iOS_Framework")),
        ],
    targets: [
        .binaryTarget(
            name: "CurrencyExchangeKMP",
            dependencies: ["iOS_Framework_Debug", "iOS_Framework"],
            path: "./CurrencyExchangeKMP.xcframework"
        )
    ]
)
