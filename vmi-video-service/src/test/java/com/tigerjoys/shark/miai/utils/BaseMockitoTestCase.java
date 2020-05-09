package com.tigerjoys.shark.miai.utils;

import junit.framework.TestCase;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MOCK基类
 * @author chengang
 *
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseMockitoTestCase extends TestCase {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
}
