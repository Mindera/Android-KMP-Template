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
    targets: [
        .binaryTarget(
            name: "CurrencyExchangeKMP",
            path: "./CurrencyExchangeKMP.xcframework"
        )
    ]
)
