package com.ugarsoft.chyma.iwin.setup;

import com.ugarsoft.chyma.iwin.models.MessagePriority;

import java.util.ArrayList;

/**
 * Created by Chyma on 5/31/2016.
 */
public class PrioritySetup {

    public static ArrayList<MessagePriority> getAllMessagePriority(){
        ArrayList<MessagePriority> priorities = new ArrayList<>();
        MessagePriority p = new MessagePriority();
        p.setName("Low");
        MessagePriority p1 = new MessagePriority();
        p1.setName("Medium");
        MessagePriority p2 = new MessagePriority();
        p2.setName("High");
        MessagePriority p3 = new MessagePriority();
        p3.setName("Urgent");
        MessagePriority p4 = new MessagePriority();
        p4.setName("Emergency");
        MessagePriority p5 = new MessagePriority();
        p5.setName("Critical");

        priorities.add(p);
        priorities.add(p1);
        priorities.add(p2);
        priorities.add(p3);
        priorities.add(p4);
        priorities.add(p5);

        return priorities;

    }
}
