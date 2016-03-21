package org.demo.selenium.base;

import java.util.ArrayList;
import java.util.List;

public class RegionManager {
	private List<Region> regions = new ArrayList<Region>();

	
	public List<Region> getRegions() {
		return regions;
	}


	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}


	public Region getRegion(String name){
		for(Region region: regions){
			if(name.equals(region.getName())){
				return region;
			}
		}
		return null;
	}
}
