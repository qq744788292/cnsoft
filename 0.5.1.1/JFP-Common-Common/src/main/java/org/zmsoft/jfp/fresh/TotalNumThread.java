package org.zmsoft.jfp.fresh;

/**
 * 运行计数器
 * 
 * @author fcy
 *
 */
public class TotalNumThread {

	private int numSuccess = 0;

	public synchronized void setNumSuccess() {
		this.numSuccess = this.numSuccess + 1;
		System.out.println("===numSuccess===" + numSuccess + "======");
	}

	private int numFail = 0;

	public synchronized void setNumFail() {
		this.numFail = this.numFail + 1;
		System.out.println("=====numFail===" + numFail + "======");
	}
}
