package com.model;

public class Monitor {
	
	double mem_used_pct;
	Disc disc_space_avail[];
	double cpu_used_pct;
	
	public Monitor(double mem, Disc disc[], double cpu) {
		mem_used_pct = mem;
		disc_space_avail = disc;
		cpu_used_pct = cpu;
	}
	
	/**
	 * @return the mem_used_pct
	 */
	public double getMem_used_pct() {
		return mem_used_pct;
	}
	/**
	 * @param mem_used_pct the mem_used_pct to set
	 */
	public void setMem_used_pct(double mem_used_pct) {
		this.mem_used_pct = mem_used_pct;
	}
	/**
	 * @return the disc_space_avail
	 */
	public Disc[] getDisc_space_avail() {
		return disc_space_avail;
	}
	/**
	 * @param disc_space_avail the disc_space_avail to set
	 */
	public void setDisc_space_avail(Disc[] disc_space_avail) {
		this.disc_space_avail = disc_space_avail;
	}
	/**
	 * @return the cpu_used_pct
	 */
	public double getCpu_used_pct() {
		return cpu_used_pct;
	}
	/**
	 * @param cpu_used_pct the cpu_used_pct to set
	 */
	public void setCpu_used_pct(double cpu_used_pct) {
		this.cpu_used_pct = cpu_used_pct;
	}
	
	
	

}
