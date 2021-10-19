package queryResponse;

import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import pojoClass.graphQLQuery;
import pojoClass.graphQlMutationQuery;

import java.util.UUID;

import static graphql.com.google.common.base.Predicates.equalTo;
import static io.restassured.RestAssured.given;


@Test
public class graphQlMutationQueryRes {



    public void addUser() {

        graphQLQuery query = new graphQLQuery();
        query.setQuery("mutation insert_users ($id: uuid!, $name: String!, $rocket: String!) { insert_users(objects: {id: $id, name: $name, rocket: $rocket}) { returning { id name rocket } } }");

        graphQlMutationQuery myUser = new graphQlMutationQuery(
                UUID.randomUUID(),
                "kim taehyung",
                "My awesome BTS",
                "www.bts.twitter.com"

        );
        query.setVariables(myUser);

        given().log().all().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://api.spacex.land/graphql/").
                then().
                assertThat().
                statusCode(200).
                and().log().all().
                body("data.insert_users.returning[0].id", (Matcher<?>) equalTo(myUser.getId().toString())).
                body("data.insert_users.returning[0].name",(Matcher<?>)  equalTo(myUser.getName())).
                body("data.insert_users.returning[0].rocket",(Matcher<?>)  equalTo(myUser.getRocket()));
    }

//    public void deleteUser(){
//        graphQLQuery query = new graphQLQuery();
//        query.setQuery("mutation delete_user($id: uuid!) {\n" +
//                "  delete_users(where :{id:{_eq:$id}}){\n" +
//                "    returning{\n" +
//                "      name\n" +
//                "      rocket\n" +
//                "      twitter\n" +
//                "\n" +
//                "    }\n" +
//                "  }\n" +
//                "}");
//
//        graphQlMutationQuery myUser = new graphQlMutationQuery(
//                UUID.randomUUID(),
//                "kim taehyung",
//                "My awesome BTS",
//                "www.bts.twitter.com"
//        );
//        query.setVariables(myUser);
//
//        given().log().all().
//                contentType(ContentType.JSON).
//                body(query).
//                when().
//                post("https://api.spacex.land/graphql/").
//                then().
//                assertThat().
//                statusCode(200).
//                and().log().all().
//                body("data.insert_users.returning[0].id", (Matcher<?>) equalTo(myUser.getId().toString())).
//                body("data.insert_users.returning[0].name",(Matcher<?>)  equalTo(myUser.getName())).
//                body("data.insert_users.returning[0].rocket",(Matcher<?>)  equalTo(myUser.getRocket()));
//
//    }
}
