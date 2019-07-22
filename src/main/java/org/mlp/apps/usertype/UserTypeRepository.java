package org.mlp.apps.usertype;

import java.util.List;
import org.mlp.apps.user.User;

public interface UserTypeRepository {

	List<User> findUser(UserType role);
}
