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
        
//        Message message = new Message("Hello World");
//          
//
//        System.out.println("Current id is " + message.getId()); 
//        session.save(message);
//        System.out.println("Current id is after save session" + message.getId());
//        
//        BillingDetails billingDetails = new CreditCard();       
//        billingDetails.setOwner("BillingDetail1");
//        ((CreditCard) billingDetails).setNumber("1234-5678-90-123");
//        session.save(billingDetails);
//        
//        billingDetails = new BankAccount();
//        billingDetails.setOwner("BillingDetail1");
//        ((BankAccount) billingDetails).setAccount("0000-00000-2343-8909");
//        session.save(billingDetails);
//        
//        MonetaryAmount monetaryAmount = new MonetaryAmount(new BigDecimal(1000), Currency.getInstance("RUB"));
//        message.setInitialPrice(monetaryAmount);
//        
//        message.addImage("imag1");
//        message.addImage("imag2");
//        message.addImage("imag3");
        
        
        Item item = new Item("Item101");

       User user = new User("User1","password1");
        
        Bid bid = new Bid(item);
        item.addBid(bid);
        session.save(bid);
        
        user.addBid(bid);
        
        
        bid = new Bid(item);
        item.addBid(bid);
        session.save(bid);
        
        user.addBid(bid);
        
        AddressEntity addressEntity = new AddressEntity("Mendeleeva", "603011", "N.Novgorod");
        addressEntity.setUser(user);
        session.save(addressEntity);
        
        user.setShippingAddress(addressEntity);
        
       session.save(item);       
       session.save(user);
        
        
//        session.save(message);

        
        
        tx.commit();
        session.close();

        // ############################################################################

        // Second unit of work
        Session secondSession = HibernateUtil.getSessionFactory().openSession();
        Transaction secondTransaction = secondSession.beginTransaction();

//        @SuppressWarnings("unchecked")
//		List<Message> messages = 
//            secondSession.createQuery("from Message m order by m.text asc").list();
//
//        System.out.println( messages.size() + " message(s) found:" );
//
//        for ( Iterator<Message> iter = messages.iterator(); iter.hasNext(); ) {
//            Message loadedMsg = iter.next();
//            System.out.println( loadedMsg.getText() );
//        }
        
        @SuppressWarnings("unchecked")
        List<Item> items = 
              secondSession.createQuery("from Item i order by i.name asc").list();
        @SuppressWarnings("unchecked")
        List<User> users = 
              secondSession.createQuery("from User u order by u.username asc").list();	
        
        System.out.println( items.size() + " items(s) found:" );
        System.out.println( users.size() + " users(s) found:" );

//        for (Iterator<Item> iter = items.iterator(); iter.hasNext(); ) {
//        	 Item loadedItm = iter.next();
//             System.out.println(loadedItm +" / "+ loadedItm.getName());
//             System.out.println("===============Bids===============");
//             List<Bid> bids = loadedItm.getBids();
//             for (Iterator<Bid> iter1 = bids.iterator(); iter1.hasNext(); ) {
//            	 Bid loadedBid = iter1.next();
//                 System.out.println(loadedBid + " / " + loadedItm.getName());	 
//             }
//        }       
        secondTransaction.commit();
        secondSession.close();

        // ############################################################################

//        // Third unit of work
        Session thirdSession = HibernateUtil.getSessionFactory().openSession();
        Transaction thirdTransaction = thirdSession.beginTransaction();

        users = thirdSession.createQuery("from User u order by u.username asc").list();	
        System.out.println(users);
        
        Query qr = thirdSession.createQuery("from Item i where i.name = 'Item101'");
        qr.setMaxResults(1);
        Item anItem = (Item) qr.uniqueResult();
        
        System.out.println(anItem);
//        thirdSession.delete(anItem);
//
//        // message.getId() holds the identifier value of the first message
//        Message loadedMessage = (Message) thirdSession.get( Message.class, message.getId());
//        message = new Message("Take me to your leader (please)");
//        message.addImage("imag1.2");
//        message.addImage("imag2.2");
//        message.addImage("imag3.2");        
//
//        loadedMessage.setText("Greetings Earthling");
//        loadedMessage.setNextMessage(message);
//        
//        //loadedMessage.setInitialPrice(new MonetaryAmount(new BigDecimal(1000), Currency.getInstance("EUR")));
//        		
//        System.out.println("-----------------------------MonetaryAmount-----------------------------");
//        System.out.println(loadedMessage.getInitialPrice());
//        
//        System.out.println("\n\nList of BillingDetail = "+HibernateUtil.getRecordsOfType(BillingDetails.class));
//        
//
        thirdTransaction.commit();
        thirdSession.close();

        // ############################################################################

        // Final unit of work (just repeat the query)
        // TODO: You can move this query into the thirdSession before the commit, makes more sense!
//        Session fourthSession = HibernateUtil.getSessionFactory().openSession();
//        Transaction fourthTransaction = fourthSession.beginTransaction();
//
//        messages =
//            fourthSession.createQuery("from Message m order by m.text asc").list();
//
//        System.out.println( messages.size() + " message(s) found:" );
//
//        for ( Iterator<Message> iter = messages.iterator(); iter.hasNext(); ) {
//            Message loadedMsg = iter.next();
//            System.out.println( loadedMsg.getText() );
//            for (String im:loadedMsg.getImages()) {
//            	System.out.println(im);	
//            }
//        }
//
//        fourthTransaction.commit();
//        fourthSession.close();


        // Shutting down the application
        HibernateUtil.shutdown();
    }
}