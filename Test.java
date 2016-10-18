/**
 * 
 */
package com.service;

import java.io.File;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

/**
 * @author Nanda
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Runtime r = Runtime.getRuntime();
		 
		double freeSpace = r.freeMemory() / (1024 * 1024);
		double totalSpace = r.totalMemory() / (1024 * 1024);
		
		System.out.println(totalSpace + ":" + freeSpace);
		
		double usedSpace = totalSpace - freeSpace;
		System.out.println((usedSpace * 100) / totalSpace);
	
		
		OperatingSystemMXBean operatingSystemMXBean = 
		          (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		       //System.out.println(operatingSystemMXBean.getProcessCpuLoad());
		
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
	    int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
	    long prevUpTime = runtimeMXBean.getUptime();
	    long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
	    double cpuUsage;
	    try 
	    {
	        Thread.sleep(500);
	    } 
	    catch (Exception ignored) { }

	    operatingSystemMXBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	    long upTime = runtimeMXBean.getUptime();
	    long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
	    long elapsedCpu = processCpuTime - prevProcessCpuTime;
	    long elapsedTime = upTime - prevUpTime;

	    cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
	    System.out.println("Java CPU: " + cpuUsage);
	}

}
