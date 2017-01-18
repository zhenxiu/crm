package com.atguigu.crm.test;

class Phone
{
	public static synchronized void getIOS() throws InterruptedException
	{
		Thread.sleep(3000);
		System.out.println("getIOS-----111");
	}
	
	public static synchronized void getAndroid()throws InterruptedException
	{
		System.out.println("getAndroid------222");
	}
	
//	public void hello()
//	{
//		System.out.println("hello------3333");
//	}
}

public class ThreadDemo
{
	public static void main(String[] args)
	{
		final Phone phone = new Phone();
		final Phone phone2 = new Phone();
		
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					phone.getIOS();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		},"AA").start();
		
			
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					phone.getAndroid();
				} catch (InterruptedException e){
					e.printStackTrace();
				}
			}
		},"BB").start();
		
//		new Thread(new Runnable()
//		{
//			@Override
//			public void run()
//			{
//				phone.hello();
//			}
//		},"CC").start();		
		
		
		
	}
}
