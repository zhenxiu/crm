package com.atguigu.crm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class Test00 {

	
	@Test
	public void testList(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		for(int i=0;i<list.size();i++){
			list.remove(i);
		}
		
		
		System.out.println(list.size());
	}
	
	@Test
	public void testPrint() throws IOException{
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		for (int j = 1; j <= num; j++) {  
            int times = (2*j)-1;  
            int time=0;  
            while(time!=(num-j))  
            {  
                System.out.print(" ");  
                time++;  
            }  
            while (times != 0) {  
                System.out.print("*");  
                times--;  
            }  
            System.out.print("\n");  
        }  
	}

}
