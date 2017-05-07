package org.lcbzr.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.lcbzr.dao.SearchDao;
import org.lcbzr.helper.HaversineDistance;
import org.lcbzr.model.Shop;
import org.lcbzr.model.UserLocation;

@Path("/search")
public class SearchService {

	
	@POST
	//@Consumes("application/x-www-form-urlencoded")
	@Consumes("application/json")
	@Produces("application/json")
	public List<Shop> getShops(UserLocation userLocation){
		List<Shop> newShops=new ArrayList<>();
		//System.out.println("Latitude= "+userLocation.getLat());
		//System.out.println(userLocation.getLon());
		HaversineDistance haversineDistance=new HaversineDistance();
		SearchDao searchDao=new SearchDao();
		List<Shop> shops= searchDao.getShopsDummy(userLocation.getProductId());
		for(Shop shop:shops){
			
			double distance=haversineDistance.calculate(userLocation.getLat(), userLocation.getLon(), shop.getLat(), shop.getLon());
			
			System.out.println(shop.getLat()+" "+" "+shop.getLon()+" "+distance);
			if(distance>5000.00D){
				//shops.remove(shop);
			}
			else{
				newShops.add(shop);
				System.out.println("Else vali "+shop.getLat()+" "+" "+shop.getLon()+" "+distance);
			}
			
		}
		
		return newShops;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/test")
	public List<Shop> test(){
		
		System.out.println("Endpoint is called");
		SearchDao searchDao=new SearchDao();
		//System.out.println(searchDao.getShopsDummy(pname).toString());
		return searchDao.getShops();
		
	}
	
}
