package jp.gr.java_conf.kou512.rakuwari;

import java.util.ArrayList;

import jp.gr.java_conf.kou512.rakuwari.bean.PersonBean;

import android.app.Application;

public class Globals extends Application {
	int totalCost;
	int costPerPerson;
	ArrayList<PersonBean> personArrayList;
	
	public void initAllGlobals(){
		totalCost = 0;
		costPerPerson = 0;
		personArrayList = null;
	}
}
