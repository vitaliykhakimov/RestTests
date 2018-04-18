package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import users.User;
import utils.Basic;
import utils.UserCreate;

import static org.assertj.core.api.Assertions.assertThat;

public class UserPost extends Basic {
    private User postUser;
    private String userLocation;

    @Before
    public void setUp() {
        postUser = UserCreate.createDummyUser();
    }

    @Test
    public void shouldCreateUser() {
        userLocation = createResource("user/", postUser);
        User getUser = getResource(userLocation, User.class);
        assertThat(getUser).isEqualToIgnoringGivenFields(postUser, "id");
    }

    @Test(expected = AssertionError.class)
    public void creatingUserWithPresentName() {
        userLocation = createResource("user/", UserCreate.creatingUserWithPresentName());
    }

    @After
    public void shutDown() {
        try{
            Basic.deleteByIdResource(userLocation);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
