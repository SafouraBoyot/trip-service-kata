package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.Test;

public class TripDAOShould {

    @Test(expected = CollaboratorCallException.class) public void
    throw_an_exception_when_retrieving_user_trips(){
        User user = new User();
        new TripDAO().tripsBy(user);
    }

}
