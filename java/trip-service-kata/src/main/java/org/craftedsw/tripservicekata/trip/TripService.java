package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

public class TripService {

    @Autowired
    private TripDAO tripDAO;
    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    public List<Trip> getFriendsTrips(User friend, User loggedInUser) throws UserNotLoggedInException {
        validate(loggedInUser);
        if (isFriendsWithLoggedInUser(friend, loggedInUser))
            return findTripsByUser(friend);
        else
            return NO_TRIPS;
    }

    private void validate(User loggedInUser) {
        ofNullable(loggedInUser)
                .orElseThrow(UserNotLoggedInException::new);
    }

    private boolean isFriendsWithLoggedInUser(User user, User loggedInUser) {
        return user.isFriendsWith(loggedInUser);
    }

    private List<Trip> findTripsByUser(User user) {
        return tripDAO.tripsBy(user);
    }


}
