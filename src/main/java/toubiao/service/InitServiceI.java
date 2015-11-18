package toubiao.service;

public interface InitServiceI {

	public abstract void init();
	public abstract void updateModule();

	public abstract void initResource();
	
	public abstract void initResourceType();
	public abstract void initRole();
	public abstract void initUser();
	public abstract void initProjectPhoto(String webAbsPath);
	

}