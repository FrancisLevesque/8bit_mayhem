package com.thedistrictheat.gameobjects;

import java.util.List;

public class ScrollHandler {
	private List<Scrollable> list;
	
    public ScrollHandler(List<Scrollable> list) {  
    	this.list = list;
    }
    
    public void update(float delta) {
    	for(int i = 0;i < list.size();i++) {
    		list.get(i).update(delta);
	        if (list.get(i).isScrolledLeft()) {
	        	list.get(i).reset();
	        }
    	}
    }
    
    public void start() {
    	for(int i = 0;i < list.size();i++) {
	        list.get(i).start();
    	}
    }
    
    public void restart() {
    	for(int i = 0;i < list.size();i++) {
	        list.get(i).restart();
    	}
    }
    
    public void stop() {
    	for(int i = 0;i < list.size();i++) {
	        list.get(i).stop();
    	}
    }
    
    public void reset() {
    	for(int i = 0;i < list.size();i++) {
	        list.get(i).reset();
    	}
    }
}
