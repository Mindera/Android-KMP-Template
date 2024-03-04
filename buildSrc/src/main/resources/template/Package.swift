// swift-tools-version: $toolsVersion

import PackageDescription

let version = "$libVersion"

let package = Package(
    name: "${name}",
    platforms: [
        $platforms
    ],
    products: [
        .library(
            name: "${name}",
            targets: ["${name}"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "${name}",
            path: "./${name}.xcframework"
        )
    ]
)
