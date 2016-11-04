/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.listener;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.dao.LotoOnlineDao;

import inet.util.DateUtil;

/**
 *
 * @author 24h
 */
public class LotoOnlineListener implements ServletContextListener, Runnable {

	private boolean running = true;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Run lotoOnline...");
		new Thread(this).start();
		System.out.println("Run lotoOnline Started");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		running = false;
	}

	@Override
	public void run() {
		while (running) {
			try {
				Date date = new Date();
				String sHHmmss = DateUtil.date2String(date, "HHmmss");
				int HHmmss = Integer.parseInt(sHHmmss);
				if (HHmmss == 80000) {
					new LotoOnlineDao().handleInsertLotoOnline();
				}
			} catch (Exception ex) {
				Logger.getLogger(CrawlerDataLive.class.getName()).log(Level.SEVERE, null, ex);
				ex.printStackTrace();
			}
		}
	}

}
