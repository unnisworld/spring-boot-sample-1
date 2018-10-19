package com.mytaxi.dataaccessobject;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

public class DriverSpecifications 
{
	@SuppressWarnings("serial")
	public static Specification<DriverDO> usernameLike(String username)
	 {
		    return new Specification<DriverDO>() 
		    {
				@Override
				public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
				{
					return builder.like(builder.lower(root.get("username")), builder.lower(builder.literal(username + "%")));
				}
			}; 
	 }
	 
	
	 @SuppressWarnings("serial")
	 public static Specification<DriverDO> onlineStatusIs(OnlineStatus onlineStatus)
	 {
		    return new Specification<DriverDO>() 
		    {
				@Override
				public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
				{
					return builder.equal(root.get("onlineStatus"), onlineStatus);
				}
			}; 
	 }
	 
	 
	 @SuppressWarnings("serial")
	 public static Specification<DriverDO> licensePlateLike(String licensePlate)
	 {
		    return new Specification<DriverDO>() 
		    {
				@Override
				public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
				{
					return builder.like(builder.lower(root.join("car").get("licensePlate")), builder.lower(builder.literal(licensePlate + "%")));
				}
			}; 
	 }
	 
	 
	 @SuppressWarnings("serial")
	 public static Specification<DriverDO> ratingGreaterThan(Float rating)
	 {
		    return new Specification<DriverDO>() 
		    {
				@Override
				public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
				{
					return builder.greaterThan(root.join("car").get("rating"), rating);
				}
			}; 
	 }
}
