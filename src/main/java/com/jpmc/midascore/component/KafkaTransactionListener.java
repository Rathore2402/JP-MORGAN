package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTransactionListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaTransactionListener.class);

    @Value("${general.kafka-topic}")
    private String topic;

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(Transaction transaction) {
        logger.info("Received transaction from topic '{}': {}", topic, transaction);
        // For now, just log the transaction - later we'll process it
        logger.info("Transaction details - Sender: {}, Recipient: {}, Amount: {}",
                transaction.getSenderId(), transaction.getRecipientId(), transaction.getAmount());
        
        // Process the transaction
        processTransaction(transaction);
    }
    
    private void processTransaction(Transaction transaction) {
        try {
            // Implement transaction processing logic here
            logger.info("Processing transaction: {}", transaction);
            // Example: Save transaction to the database or perform business logic
            
            // Validate transaction amount
            if (transaction.getAmount() <= 0) {
                logger.warn("Invalid transaction amount: {}", transaction.getAmount());
                return;
            }
            
            logger.info("Transaction processed successfully: {}", transaction);
        } catch (Exception e) {
            logger.error("Error processing transaction: {}", e.getMessage());
        }
    }
}
