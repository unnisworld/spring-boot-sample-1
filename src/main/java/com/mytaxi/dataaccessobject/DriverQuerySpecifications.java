package com.mytaxi.dataaccessobject;

import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mytaxi.datatransferobject.DriverSearchCriteriaDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;

/**
 * Contains Specifications for querying driver table.
 * <p/>
 */
public class DriverQuerySpecifications 
{
	@SuppressWarnings("serial")
	public static Specification<DriverDO> usernameLike(String username)
	 {
		    return new Specification<DriverDO>() 
		    {
				@Override
				public Predicate toPredicate(Root<DriverDO> root, CriteriaQuery<?> query, CriteriaBuilder builder) 
				{
					return builder.like(builder.lower(root.get("username")), builder.lower(builder.literal("%" + username + "%")));
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
					return builder.like(builder.lower(root.join("car").get("licensePlate")), builder.lower(builder.literal("%" + licensePlate + "%")));
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
	 
    public static Optional<Specification<DriverDO>> buildQuerySpecification(DriverSearchCriteriaDTO searchInput) 
    {
    	SpecificationBuilder specBuilder = new SpecificationBuilder();
    	
    	Specification<DriverDO> spec = null;
    	if (searchInput.getUsernameContains() != null) 
    	{
    		spec = specBuilder.add(usernameLike(searchInput.getUsernameContains()));
    	}
    	
    	if (searchInput.getOnlineStatus() != null)
    	{
    		spec = specBuilder.add(onlineStatusIs(searchInput.getOnlineStatus()));
    	}
    	
    	if (searchInput.getLicensePlateContains() != null)
    	{
    		spec = specBuilder.add(licensePlateLike(searchInput.getLicensePlateContains()));
    	}
    	
    	if (searchInput.getRatingGreaterThan() != null)
    	{
    		spec = specBuilder.add(ratingGreaterThan(searchInput.getRatingGreaterThan()));
    	}
    	
    	return Optional.ofNullable(spec);
    }
    
    
    private static class SpecificationBuilder 
    {
    	private Specification<DriverDO> combinedSpec = null;
    		
    	Specification<DriverDO> add(Specification<DriverDO> newSpec) 
    	{
    		if (combinedSpec == null) 
    		{
    			combinedSpec = newSpec;
    		} 
    		else 
    		{
    			combinedSpec = combinedSpec.and(newSpec);
    		}
    		
    		return combinedSpec;
    	}
    }

}
