package com.quizam.mongobee;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.Arrays;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "001_insert_dummy_data", author = "ankit")
    public void insertDummyData(DB db){
        DBCollection mycollection = db.getCollection("question");
        BasicDBObject doc1 = new BasicDBObject()
                .append("_id", 1L)
                .append("question", "Which service is used to save objects in AWS?")
                .append("options", Arrays.asList("EBS", "EFS", "S3", "EKS"))
                .append("answers", Arrays.asList("S3"))
                .append("author", "Jeff Barr")
                .append("subject", "AWS")
                .append("topic", "Cloud Formation")
                .append("links", Arrays.asList())
                .append("_class", "com.quizam.domain.Question");

        BasicDBObject doc2 = new BasicDBObject()
                .append("_id", 2L)
                .append("question", "You have five CloudFormation templates; each template is for a different " +
                        "application architecture. This architecture varies between your blog apps and " +
                        "your gaming apps. What determines the cost of using the CloudFormation templates?")
                .append("options", Arrays.asList("The time it takes to build the architecture with Cloud Formation.",
                        "Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds.",
                        "0.10$ per template per month",
                        "0.1$ per template per month"))
                .append("answers", Arrays.asList("Cloud Formation does not have any additional cost but you are charged for the underlying resources it builds."))
                .append("author", "Jeff Barr")
                .append("subject", "AWS")
                .append("topic", "Storage")
                .append("links", Arrays.asList())
                .append("_class", "com.quizam.domain.Question");

        mycollection.insert(doc1);
        mycollection.insert(doc2);
    }

    @ChangeSet(order = "002", id = "002_sequence_number", author = "ankit")
    public void insertSequenceNumber(DB db){
        DBCollection mycollection = db.getCollection("database_sequences");
        BasicDBObject doc = new BasicDBObject()
                .append("_id", "question_sequence")
                .append("seq", 2);
        mycollection.insert(doc);
    }
}