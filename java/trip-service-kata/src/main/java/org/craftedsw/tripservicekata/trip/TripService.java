package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;

public class TripService {

    private static final List<Trip> NO_TRIPS = Collections.emptyList();

    public List<Trip> getTripsByUser(User user, User loggedInUser) throws UserNotLoggedInException {
        if (isFriendsWithLoggedInUser(user,loggedInUser ))
            return findTripsByUser(user);
        else
            return NO_TRIPS;
    }

    private boolean isFriendsWithLoggedInUser(User user, User loggedInUser) {
         ofNullable(loggedInUser)
                .orElseThrow(UserNotLoggedInException::new);
        return user.isFriendsWith(loggedInUser);
    }

    protected List<Trip> findTripsByUser(User user) {
        return TripDAO.findTripsByUser(user);
    }


}
