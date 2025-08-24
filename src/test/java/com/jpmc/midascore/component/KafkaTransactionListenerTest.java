package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaTransactionListenerTest {

    @InjectMocks
    private KafkaTransactionListener kafkaTransactionListener;

    @Mock
    private Logger logger;

    @BeforeEach
    void setUp() {
        // Inject the topic value
        ReflectionTestUtils.setField(kafkaTransactionListener, "topic", "test-topic");
        
        // Mock the logger
        Logger originalLogger = LoggerFactory.getLogger(KafkaTransactionListener.class);
        ReflectionTestUtils.setField(kafkaTransactionListener, "logger", logger);
    }

    @Test
    void testListen_ValidTransaction_ShouldLogAndProcess() {
        // Arrange
        Transaction transaction = new Transaction(1L, 2L, 100.0f);
        
        // Act
        kafkaTransactionListener.listen(transaction);
        
        // Assert
        verify(logger).info("Received transaction from topic '{}': {}", "test-topic", transaction);
        verify(logger).info("Transaction details - Sender: {}, Recipient: {}, Amount: {}", 
                1L, 2L, 100.0f);
        verify(logger).info("Processing transaction: {}", transaction);
        verify(logger).info("Transaction processed successfully: {}", transaction);
    }

    @Test
    void testListen_InvalidTransactionAmount_ShouldLogWarning() {
        // Arrange
        Transaction transaction = new Transaction(1L, 2L, -50.0f);
        
        // Act
        kafkaTransactionListener.listen(transaction);
        
        // Assert
        verify(logger).info("Received transaction from topic '{}': {}", "test-topic", transaction);
        verify(logger).info("Transaction details - Sender: {}, Recipient: {}, Amount: {}", 
                1L, 2L, -50.0f);
        verify(logger).warn("Invalid transaction amount: {}", -50.0f);
        verify(logger, never()).info("Transaction processed successfully: {}", transaction);
    }

    @Test
    void testListen_ZeroAmountTransaction_ShouldLogWarning() {
        // Arrange
        Transaction transaction = new Transaction(1L, 2L, 0.0f);
        
        // Act
        kafkaTransactionListener.listen(transaction);
        
        // Assert
        verify(logger).info("Received transaction from topic '{}': {}", "test-topic", transaction);
        verify(logger).info("Transaction details - Sender: {}, Recipient: {}, Amount: {}", 
                1L, 2L, 0.0f);
        verify(logger).warn("Invalid transaction amount: {}", 0.0f);
        verify(logger, never()).info("Transaction processed successfully: {}", transaction);
    }

    @Test
    void testProcessTransaction_Exception_ShouldLogError() {
        // Arrange
        Transaction transaction = new Transaction(1L, 2L, 100.0f);
        
        // Mock the logger to throw an exception when processing
        doThrow(new RuntimeException("Processing failed")).when(logger).info("Processing transaction: {}", transaction);
        
        // Act
        kafkaTransactionListener.listen(transaction);
        
        // Assert
        verify(logger).info("Received transaction from topic '{}': {}", "test-topic", transaction);
        verify(logger).info("Transaction details - Sender: {}, Recipient: {}, Amount: {}", 
                1L, 2L, 100.0f);
        verify(logger).error("Error processing transaction: {}", "Processing failed");
    }
}
