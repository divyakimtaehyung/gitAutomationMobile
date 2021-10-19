package queryResponse;

import org.testng.annotations.Test;
import pojoClass.graphQLQuery;

@Test
public class graphQlMutationQuery {

    public void addUser_checkReturnedData_shouldCorrespondToDataSent() {

        graphQLQuery query = new graphQLQuery();
        query.setQuery("mutation insert_users ($id: uuid!, $name: String!, $rocket: String!) " +
                "{ insert_users(objects: {id: $id, name: $name, rocket: $rocket}) " +
                "{ returning { id name rocket } " +
                "}" +
                " }");

        graphQlMutationQuery myUser = new graphQlMutationQuery(
                UUID.randomUUID(),
                "Bas",
                "My awesome rocket"
        );
        query.setVariables(myUser);

        given().
                contentType(ContentType.JSON).
                body(query).
                when().
                post("https://api.spacex.land/graphql/").
                then().
                assertThat().
                statusCode(200).
                and().
                body("data.insert_users.returning[0].id", equalTo(myUser.getId().toString())).
                body("data.insert_users.returning[0].name", equalTo(myUser.getName())).
                body("data.insert_users.returning[0].rocket", equalTo(myUser.getRocket()));
    }
}
