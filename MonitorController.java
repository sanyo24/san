package com.service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.DecimalFormat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.model.Disc;
import com.model.Monitor;
import com.sun.management.OperatingSystemMXBean;

/*
 * Creating a Spring REST Controller to create RESTful web service.
 */

@Controller
public class MonitorController {
	
	/*
	 * This method is published as a RESTful Web Service API.
	 * It is a REST API available in the URI /status.
	 * Access this service as "http://localhost:8080/status"
	 */
	
	@RequestMapping("/status")
    public @ResponseBody Monitor statusReport(
            @RequestParam(value="name", required=false, defaultValue="World") String name) {
    	
    		double mem = findMemoryPercentage();
    		Disc disc[] = findSpaces();
    		double cpuPercent = findCPUPercent();
    		
    		// To format for only 2 decimal points.
    		DecimalFormat decimalFormat = new DecimalFormat("#.##");
    	
        return new Monitor(Double.parseDouble(decimalFormat.format(mem)), disc, Double.parseDouble(decimalFormat.format(cpuPercent)));
    }
	
	/*
	 * To calculate used memory percentage
	 */
	
	double findMemoryPercentage() {
		Runtime r = Runtime.getRuntime();
		 
		double freeSpace = r.freeMemory() / (1024 * 1024);
		double totalSpace = r.totalMemory() / (1024 * 1024);
		
		double usedSpace = totalSpace - freeSpace;
		
		return ((usedSpace * 100) / totalSpace);
	}
	
	/*
	 * To identify all the discs / drives in this system (Where REST API is running)
	 * and find out the avilable space in each of these discs.
	 * For MAC machines, it returns "/" as a disc.
	 */
	Disc[] findSpaces() {
		File[] drives = File.listRoots();
		Disc disc[] = new Disc[drives.length];
		int i=0;
		if (drives != null && drives.length > 0) {
		    for (File aDrive : drives) { 
		    		String freeSpace = "" + aDrive.getFreeSpace() / (1024 * 1024);
		    		disc[i] = new Disc();
		    		disc[i].setDiscname(aDrive.getName());
		    		disc[i].setAvailbytes(freeSpace + "MB");
		    		i++;
		    }
		}
		
		return disc;
	}
	
	/*
	 * It calculates CPU usage percentage
	 * 
	 */
	double findCPUPercent() {
		
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
	    
	    return cpuUsage;
		
	}

}
