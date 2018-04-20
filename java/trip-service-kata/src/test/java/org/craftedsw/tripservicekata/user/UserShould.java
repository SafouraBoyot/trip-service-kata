package org.craftedsw.tripservicekata.user;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserShould {
    @Test
    public void
    inform_when_users_are_not_friends() {
        User user = new User();
        User aUser = new User();

        assertThat(user.isFriendsWith(aUser), is(false));
    }

    @Test
    public void
    inform_when_users_are_friends() {
        User user = new User();
        User aUser = new User();
        user.addFriend(aUser);

        assertThat(user.isFriendsWith(aUser), is(true));
    }

}
