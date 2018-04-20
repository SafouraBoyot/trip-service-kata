package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

public class TripServiceTestShould extends TripService {

    private List<Trip> stubListOfTrips;

    @Test(expected = UserNotLoggedInException.class)
    public void
    throws_exception_if_user_not_logged_in() {

        User user = new User();
        getTripsByUser(user, null);
    }

    @Test
    public void
    find_trips_when_user_is_friend_with_logged_in_user() {
        User user = new User();
        User loggedInUser = new User();

        user.addFriend(loggedInUser);

        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        makeDaoReturn(expectedListOfTrips);
        List<Trip> result = getTripsByUser(user, loggedInUser);

        assertThat(result, is(expectedListOfTrips));

    }

    @Test
    public void
    not_find_trips_when_user_is_not_friend_with_logged_in_user() {
        User user = new User();
        User loggedInUser = new User();

        List<Trip> expectedListOfTrips = new ArrayList<>();
        expectedListOfTrips.add(new Trip());
        makeDaoReturn(expectedListOfTrips);
        List<Trip> result = getTripsByUser(user, loggedInUser);

        assertThat(result.size(), is(0));
        assertNotSame(result, expectedListOfTrips);

    }

    private void makeDaoReturn(List<Trip> expectedListOfTrips) {
        stubListOfTrips = expectedListOfTrips;
    }

    @Override
    protected List<Trip> findTripsByUser(User user) {
        return stubListOfTrips;
    }
}
