package com.deppon.demo.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepListenerSupport;
import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.List;

public class StepListener<T, S> extends StepListenerSupport<T, S> {

	private static final Logger logger = LoggerFactory
			.getLogger(StepListener.class);

	@Override
	public void onReadError(Exception ex) {
		logger.error("Encountered error on read", ex);
	}

	@Override
	public void onProcessError(T item, Exception e) {
		logger.error("Encountered error on process", e);
	}

	@Override
	public void onWriteError(Exception exception, List<? extends S> items) {
		logger.error("Encountered error on write", exception);
	}

	/*public ExitStatus afterStep(StepExecution stepExecution) {
		logger.info("{}", stepExecution.getExitStatus());
		return stepExecution.getExitStatus();
	}

	public void beforeStep(StepExecution stepexecution) {
		logger.info("beforeStep");
	}

	public void afterChunk(ChunkContext chunkcontext) {
		logger.info("afterChunk");
	}

	public void beforeChunk(ChunkContext chunkcontext) {
		logger.info("beforeChunk");
	}

	public void afterRead(Object obj) {
		logger.info("afterRead");
	}

	public void beforeRead() {
		logger.info("beforeRead");
	}

	public void afterWrite(List list) {
		logger.info("afterWrite");
	}

	public void beforeWrite(List list) {
		logger.info("beforeWrite");
	}

	public void afterProcess(Object obj, Object obj1) {
		logger.info("afterProcess");
	}

	public void beforeProcess(Object obj) {
		logger.info("beforeProcess");
	}

	public void onSkipInProcess(Object obj, Throwable throwable) {
		logger.info("onSkipInProcess");
	}

	public void onSkipInRead(Throwable throwable) {
		logger.info("onSkipInRead");
	}

	public void onSkipInWrite(Object obj, Throwable throwable) {
		logger.info("onSkipInWrite");
	}

	public void afterChunkError(ChunkContext chunkcontext) {
		logger.info("afterChunkError");
	}*/

}
