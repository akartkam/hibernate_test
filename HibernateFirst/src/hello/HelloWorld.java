package hello;

import org.hibernate.*;

import persistence.*;

import java.math.BigDecimal;
import java.util.*;

public class HelloWorld {

    public static void main(String[] args) {

        // ############################################################################

        // First unit of work
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        
        Message message = new Message("Hello World");
          

        System.out.println("Current id is " + message.getId()); 
        session.save(message);
        System.out.println("Current id is after save session" + message.getId());
        
        BillingDetails billingDetails = new CreditCard();       
        billingDetails.setOwner("BillingDetail1");
        ((CreditCard) billingDetails).setNumber("1234-5678-90-123");
        session.save(billingDetails);
        
        billingDetails = new BankAccount();
        billingDetails.setOwner("BillingDetail1");
        ((BankAccount) billingDetails).setAccount("0000-00000-2343-8909");
        session.save(billingDetails);
        
        MonetaryAmount monetaryAmount = new MonetaryAmount(new BigDecimal(1000), Currency.getInstance("RUB"));
        message.setInitialPrice(monetaryAmount);
        
        message.addImage("imag1");
        message.addImage("imag2");
        message.addImage("imag3");
        
        session.save(message);
        
        tx.commit();
        session.close();

        // ############################################################################

        // Second unit of work
        Session secondSession = HibernateUtil.getSessionFactory().openSession();
        Transaction secondTransaction = secondSession.beginTransaction();

        @SuppressWarnings("unchecked")
		List<Message> messages = 
            secondSession.createQuery("from Message m order by m.text asc").list();

        System.out.println( messages.size() + " message(s) found:" );

        for ( Iterator<Message> iter = messages.iterator(); iter.hasNext(); ) {
            Message loadedMsg = iter.next();
            System.out.println( loadedMsg.getText() );
        }

        secondTransaction.commit();
        secondSession.close();

        // ############################################################################

        // Third unit of work
        Session thirdSession = HibernateUtil.getSessionFactory().openSession();
        Transaction thirdTransaction = thirdSession.beginTransaction();

        // message.getId() holds the identifier value of the first message
        Message loadedMessage = (Message) thirdSession.get( Message.class, message.getId());
        message = new Message("Take me to your leader (please)");
        message.addImage("imag1.2");
        message.addImage("imag2.2");
        message.addImage("imag3.2");        

        loadedMessage.setText("Greetings Earthling");
        loadedMessage.setNextMessage(message);
        
        //loadedMessage.setInitialPrice(new MonetaryAmount(new BigDecimal(1000), Currency.getInstance("EUR")));
        		
        System.out.println("-----------------------------MonetaryAmount-----------------------------");
        System.out.println(loadedMessage.getInitialPrice());
        
        System.out.println("\n\nList of BillingDetail = "+HibernateUtil.getRecordsOfType(BillingDetails.class));
        

        thirdTransaction.commit();
        thirdSession.close();

        // ############################################################################

        // Final unit of work (just repeat the query)
        // TODO: You can move this query into the thirdSession before the commit, makes more sense!
        Session fourthSession = HibernateUtil.getSessionFactory().openSession();
        Transaction fourthTransaction = fourthSession.beginTransaction();

        messages =
            fourthSession.createQuery("from Message m order by m.text asc").list();

        System.out.println( messages.size() + " message(s) found:" );

        for ( Iterator<Message> iter = messages.iterator(); iter.hasNext(); ) {
            Message loadedMsg = iter.next();
            System.out.println( loadedMsg.getText() );
            for (String im:loadedMsg.getImages()) {
            	System.out.println(im);	
            }
        }

        fourthTransaction.commit();
        fourthSession.close();


        // Shutting down the application
        HibernateUtil.shutdown();
    }
}