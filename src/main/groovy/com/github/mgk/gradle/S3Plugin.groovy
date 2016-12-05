package com.github.mgk.gradle

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.S3Object
import org.gradle.api.Project
import org.gradle.api.Plugin
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction


class S3Extension {
    String region
    String bucket
}


abstract class S3Task extends DefaultTask {
    @Input
    String bucket

    @Input
    String key

    @Input
    String file

    String getBucket() { bucket ?: project.s3.bucket }

    def getS3Client() {
        def creds = new DefaultAWSCredentialsProviderChain()
        AmazonS3Client s3Client = new AmazonS3Client(creds)
        String region = project.s3.region
        if (region) {
            s3Client.region = Region.getRegion(Regions.fromName(region))
        }
        s3Client
    }
}


class S3Upload extends S3Task {
    @Input
    boolean overwrite = false

    @TaskAction
    def task() {
        if (overwrite || !s3Client.doesObjectExist(bucket, key)) {
            s3Client.putObject(bucket, key, new File(file))
        }
    }
}


class S3Download extends S3Task {
    @TaskAction
    def task() {
        S3Object s3Object = s3Client.getObject(bucket, key)
        File f = new File(file)
        f.parentFile.mkdirs()
        f.withOutputStream { out ->
            out << s3Object.objectContent
        }
    }
}


class S3Plugin implements Plugin<Project> {
    void apply(Project target) {
        target.extensions.create('s3', S3Extension)
    }
}
