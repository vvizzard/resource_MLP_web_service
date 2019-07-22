package org.mlp.apps.usertype;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.mlp.apps.user.User;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class UserTypeRepositoryImpl implements UserTypeRepository {

	@PersistenceContext
    private EntityManager em;
	
	@Override
	public List<User> findUser(UserType role) {
		TypedQuery<UserType> query = em.createQuery("Select t from  UserType t left join fetch t.users where t.id=:usertype order by random()", UserType.class);
		query.setParameter("usertype", role.getId());
		UserType type = query.getSingleResult();
		return new ArrayList<>(type.getUsers());
	}

	
}
