# S3 Gradle Plugin

Gradle plugin that uploads and downloads S3 objects.

## Usage

```
buildscript {
    repositories {
      jcenter()
    }
    dependencies {
        classpath group: 'com.github.mgk.gradle', name: 's3', version: '1.0.0'
    }
}

apply plugin: 'groovy'
apply plugin: 'com.github.mgk.gradle.s3'

s3.bucket = 'my-bucket'

// specify a region if needed
// s3.region = 'us-west-2'

// download s3://my-bucket/some-dir/my-file.txt to build/my-file.txt
task s3Download(type: com.github.mgk.gradle.S3Download) {
    key = 'some-dir/my-file.txt'
    file = "$buildDir/my-file.txt"
}

// upload hello.txt local file to s3://my-bucket/hello.txt
// if s3://my-bucket/hello.txt already exists no action is taken
task s3Upload(type: com.github.mgk.gradle.S3Upload) {
    key = 'hello.txt'
    file = 'hello.txt'
}

// upload hello.txt local file to s3://my-bucket/hello.txt
// overwriting the s3 object if it already exists
// if s3://my-bucket/hello.txt already exists no action is taken
task s3Upload(type: com.github.mgk.gradle.S3Upload) {
    key = 'hello.txt'
    file = 'hello.txt'
    overwrite = true
}

// both upload and download tasks can override the bucket
// download s3://other-bucket/my-file.txt to build/my-file.txt
task s3Download(type: com.github.mgk.gradle.S3Download) {
    bucket = 'other-bucket'
    key = 'my-file.txt'
    file = "$buildDir/my-file.txt"
}
```
