package io.pivotal.pivotalservices.logging.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LogProducer implements DisposableBean{

	private static final Logger logger = LoggerFactory.getLogger(LogProducer.class);
	private static final ExecutorService executor = Executors.newFixedThreadPool(150);
	private static final AtomicLong executionCount = new AtomicLong();
	
	@Scheduled(fixedRate = 327, initialDelay = 1000 * 5)
	public void execute(){
		for(int i = 0; i < 101; i++){
			final long index = executionCount.getAndIncrement();
			
			executor.execute(new Runnable(){
	
				@Override
				public void run() {
					if(index % 11 == 0){
						logger.error("{} Houston, we have a problem", index);
					}else if(index % 7 == 0){
						logger.info("{} Minnesota is cold in January. Good info, eh?", index);
					}else if(index % 5 == 0){
						logger.warn("{} I've got a bad feeling about this", index);
					}else{
						logger.debug("{} logging logging logging... keep those apps a logging... logging logging logging, RAWHIDE!", index);
					}
					
					if(index % 101 == 0){
						logger.error("This is a test of the exception handling system. Had this been an actual exception, you would be instructed to check and handle your exceptions accordingly", new Exception("A truly exceptional circumstance"));
					}
				}
				
			});
		}
		
	}

	@Override
	public void destroy() throws Exception {
		executor.shutdownNow();
	}
	
}
