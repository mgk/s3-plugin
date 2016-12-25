# S3 Gradle Plugin
[![Build Status](https://img.shields.io/travis/mgk/s3-plugin.svg)](https://travis-ci.org/mgk/s3-plugin)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)
![Flux Cap](https://img.shields.io/badge/flux%20capacitor-1.21%20GW-orange.svg)

Gradle plugin that uploads and downloads S3 objects.

## Setup

New way:

```groovy
plugins {
  id "com.github.mgk.gradle.s3" version "1.1.0"
}
```

Old way:

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "gradle.plugin.com.github.mgk.gradle:s3:1.1.0"
  }
}

apply plugin: "com.github.mgk.gradle.s3"
```

## Versioning

This project uses [semantic versioning](http://semver.org)

See [gradle plugin page](https://plugins.gradle.org/plugin/com.github.mgk.gradle.s3) for other versions.

# Usage

## Authentication

The S3 plugin uses the [default credentials provider](http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/com/amazonaws/auth/DefaultAWSCredentialsProviderChain.html) so setting the environment variables `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` is one way to provide your S3 credentials.

## Tasks

The following Gradle tasks are provided.

### S3Upload

Upload a file to S3. Properties:

  + `bucket` - S3 bucket to use *(optional, defaults to the project `s3` configured bucket)*
  + `file` - path of file to be uploaded
  + `key` - key of S3 object to create.
  + `overwrite` - *(optional, default is `false`)*, if `true` the S3 object is created or overwritten if it already exists.

By default `S3Upload` does not overwrite the S3 object if it already exists. Set `overwrite` to `true` to upload the file even if it exists.

### S3Download

Downloads one or more S3 objects. This task has two modes of operation: single file
download and recursive download. Properties that apply to both modes:

  + `bucket` - S3 bucket to use *(optional, defaults to the project `s3` configured bucket)*

For a single file download:

  + `key` - key of S3 object to download
  + `file` - local path of file to save the download to

For a recursive download:

  + `keyPrefix` - S3 prefix of objects to download
  + `destDir` - local directory to download objects to

## Progress Reporting

Downloads report percentage progress at the gradle INFO level. Run gradle with the `-i` option to see download progress.


## License
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](LICENSE)
