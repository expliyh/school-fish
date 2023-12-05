package top.expli.schoolfish;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Item {

	@Id
	int iID;

	String profile;

	String uID;

	String price;

	String iName;

	String iState;//商品状态，在售，已售，下架

	public String getuID(){return uID;}

	public void setuID(String uID){this.uID = uID;}

	public String getPrice(){return price;}

	public void setPrice(String price){this.price = price;}

	public String getiName(){return iName;}

	public void setiName(String iName){this.iName = iName;}

	public int getiID(){return iID;}

	public void setiID(int iID){this.iID = iID;}

	public String getProfile(){return profile;}

	public void setProfile(String profile){this.profile = profile;}

	public String getiState(){return iState;}

	public void setiState(String iState){this.iState = iState;}

	void Item(int iID,String iName,String price,String uID,String profile,String iState){
		this.iID = iID;
		this.iName = iName;
		this.price = price;
		this.uID = uID;
		this.profile = profile;
		this.iState = iState;
	}

}
