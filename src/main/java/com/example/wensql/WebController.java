package com.example.wensql;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private Customer customer;

    @Autowired
    private CustomerController cusCont;

    @Autowired
    private RoomController roomCont;

    @GetMapping("/index")
    public String getMethodName(Model model) {
        return "main";
    }

    // admin user database
    // room view salgah
    // buh customeriin medeelliig harah, uurchluh
    @PostMapping("/index")
    public String postMethodName(@RequestParam(name = "button") String val, @ModelAttribute Customer cus,
            @ModelAttribute Room newRoom,
            @RequestParam(name = "code") String code, @RequestParam(name = "pass") String pass, Model model) {
        System.out.println("\n\n\n\n\n\n\n\n\n");
        if (val.equals("back")) {
            return "main";
        }
        if (val.equals("create")) {
            return "customer";
        }
        if (val.equals("login")) {
            model.addAttribute("value", "");
            return "login";
        }

        if (val.equals("signin")) {
            Customer c = cusCont.Get(code);
            if (c == null) {
                model.addAttribute("value", "invalid Code");
                return "login";
            }
            if (c.getPass().equals("2," + pass)) {
                customer = c;
                if (!customer.isAdmin()) {
                    model.addAttribute("price", GetAllPrice(customer.getCusCode()));
                    // model.addAttribute("roomElem", ShowRooms());
                    model.addAttribute("empRoomElem", ShowEmptyRooms());
                    model.addAttribute("noRoomElem", ShowNoEmptyRooms());
                    model.addAttribute("userName", c.getCusFName());
                    return "show";
                } else {
                    model.addAttribute("allRoomElem", ShowRoomsWithAdmin());
                    model.addAttribute("emptyRoomElem", ShowEmptyRoomsWithAdmin());
                    model.addAttribute("noEmptyRoomElem", ShowNoEmptyRoomsWithAdmin());
                    model.addAttribute("allCustElem", ShowCustWithAdmin());
                    return "room";
                }
            } else {

                model.addAttribute("value", "invalid Password");
                return "login";
            }
        }
        if (val.equals("createCus")) {
            customer = cus;
            cusCont.Add(cus);
            // model.addAttribute("roomElem", ShowRooms());
            model.addAttribute("empRoomElem", ShowEmptyRooms());
            model.addAttribute("noRoomElem", ShowNoEmptyRooms());
            model.addAttribute("userName", cus.getCusFName());
            return "show";
        }
        if (val.equals("room")) {
            System.out.println("\n\n123\n\n");
            Room room = roomCont.Get(Integer.parseInt(code));
            if (room != null) {
                if (customer.getCusCode().equals(room.getCusCode())) {
                    room.setCusCode("");
                    room.setrDate("");
                    roomCont.Update(room);
                } else {
                    room.setCusCode(customer.getCusCode());
                    room.setrDate(LocalDate.now().toString());
                    roomCont.Update(room);
                }
            }
            model.addAttribute("price", GetAllPrice(customer.getCusCode()));
            model.addAttribute("userName", customer.getCusFName());
            // model.addAttribute("roomElem", ShowRooms());
            model.addAttribute("empRoomElem", ShowEmptyRooms());
            model.addAttribute("noRoomElem", ShowNoEmptyRooms());
            return "show";
        }
        if (val.equals("admin-createRoom")) {
            roomCont.Add(newRoom);
            model.addAttribute("allRoomElem", ShowRoomsWithAdmin());
            model.addAttribute("emptyRoomElem", ShowEmptyRoomsWithAdmin());
            model.addAttribute("noEmptyRoomElem", ShowNoEmptyRoomsWithAdmin());
            model.addAttribute("allCustElem", ShowCustWithAdmin());
            return "room";
        }
        if (val.equals("admin-deleteRoom")) {
            roomCont.Delete(Integer.parseInt(code));
            model.addAttribute("allRoomElem", ShowRoomsWithAdmin());
            model.addAttribute("emptyRoomElem", ShowEmptyRoomsWithAdmin());
            model.addAttribute("noEmptyRoomElem", ShowNoEmptyRoomsWithAdmin());
            model.addAttribute("allCustElem", ShowCustWithAdmin());
            return "room";
        }
        if (val.equals("admin-emptyRoom")) {
            Room r = roomCont.Get(Integer.parseInt(code));
            r.setCusCode("");
            r.setrDate("");
            roomCont.Update(r);
            model.addAttribute("allRoomElem", ShowRoomsWithAdmin());
            model.addAttribute("emptyRoomElem", ShowEmptyRoomsWithAdmin());
            model.addAttribute("noEmptyRoomElem", ShowNoEmptyRoomsWithAdmin());
            model.addAttribute("allCustElem", ShowCustWithAdmin());
            return "room";
        }
        if (val.equals("admin-deleteCust")) {
            Customer c = cusCont.Get(code);
            List<Room> rooms = roomCont.GetAll();
            for (int i = 0; i < rooms.size(); i++) {
                if (rooms.get(i).getCusCode().equals(c.getCusCode())) {
                    rooms.get(i).setCusCode("");
                    rooms.get(i).setrDate("");
                    roomCont.Update(rooms.get(i));
                }
            }
            cusCont.Delete(code);
            model.addAttribute("allRoomElem", ShowRoomsWithAdmin());
            model.addAttribute("emptyRoomElem", ShowEmptyRoomsWithAdmin());
            model.addAttribute("noEmptyRoomElem", ShowNoEmptyRoomsWithAdmin());
            model.addAttribute("allCustElem", ShowCustWithAdmin());
            return "room";
        }
        return "main";
    }

    public int GetAllPrice(String name) {
        List<Room> rooms = roomCont.GetAll();
        int price = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCusCode().equals(name)) {
                price += rooms.get(i).getPrice();
            }
        }
        return price;
    }

    public String ShowRooms() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).getCusCode().equals(customer.getCusCode()) && !rooms.get(i).getCusCode().equals("")) {
                continue;
            }

            str += "<div class=\"elem\">";
            str += GetRoomElem(rooms.get(i).getRoomId() + "");
            str += "</div>";
        }
        return str;
    }

    public String ShowEmptyRooms() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).getCusCode().equals(customer.getCusCode()) && !rooms.get(i).getCusCode().equals("")) {
                continue;
            }
            if (roomCont.Get(rooms.get(i).getRoomId()).getCusCode().equals("")) {
                str += "<div class=\"elem\">";
                str += GetRoomElem(rooms.get(i).getRoomId() + "");
                str += "</div>";
            }
        }
        return str;
    }

    public String ShowNoEmptyRooms() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).getCusCode().equals(customer.getCusCode()) && !rooms.get(i).getCusCode().equals("")) {
                continue;
            }
            if (roomCont.Get(rooms.get(i).getRoomId()).getCusCode().equals(customer.getCusCode())) {
                str += "<div class=\"elem\">";
                str += GetRoomElem(rooms.get(i).getRoomId() + "");
                str += "</div>";
            }
        }
        return str;
    }

    public String ShowRoomsWithAdmin() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            str += "<div class=\"elem\">";
            str += GetRoomElemWithAdmin(rooms.get(i).getRoomId() + "");
            str += "</div>";
        }
        return str;
    }

    public String ShowEmptyRoomsWithAdmin() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getCusCode().equals("")) {
                str += "<div class=\"elem\">";
                str += GetRoomElemWithAdmin(rooms.get(i).getRoomId() + "");
                str += "</div>";
            }
        }
        return str;
    }

    public String ShowNoEmptyRoomsWithAdmin() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Room> rooms = roomCont.GetAll();
        for (int i = 0; i < rooms.size(); i++) {
            if (!rooms.get(i).getCusCode().equals("")) {
                str += "<div class=\"elem\">";
                str += GetRoomElemWithAdmin(rooms.get(i).getRoomId() + "");
                str += "</div>";
            }
        }
        return str;
    }

    public String ShowCustWithAdmin() {
        String str = "";
        System.out.println("\n1111111\n" + roomCont.GetAll().size());
        List<Customer> cs = cusCont.GetAll();
        for (int i = 0; i < cs.size(); i++) {
            if (!cs.get(i).getCusCode().equals("admin") && !cs.get(i).getCusCode().equals("")) {
                str += "<div class=\"elem\">";
                str += GetCusElemWithAdmin(cs.get(i).getCusCode() + "");
                str += "</div>";
            }

        }
        return str;
    }

    public String GetRoomElemWithAdmin(String num) {
        Room r = roomCont.Get(Integer.parseInt(num));
        String str = "<div style='display:flex; flex-direction:row;'><form th:action = \"@{/input}\", method='post'>";
        str += "<input name=\"button\" type=\"hidden\" value=" + "admin-deleteRoom" + ">";
        str += "<input name=\"code\" type=\"hidden\" value=" + num + ">";
        str += "<input name=\"pass\" type=\"hidden\" value=\"back\">";
        str += "<input type=\"submit\" value = " + "Delete" + ">";
        str += "</form>";
        str += "<form th:action = \"@{/input}\", method='post'>";
        str += "<input name=\"button\" type=\"hidden\" value=" + "admin-emptyRoom" + ">";
        str += "<input name=\"code\" type=\"hidden\" value=" + num + ">";
        str += "<input name=\"pass\" type=\"hidden\" value=\"back\">";
        str += "<input type=\"submit\" value = " + "Empty" + ">";
        str += "</form></div>";
        str += "&nbsp;&nbsp;Name: " + num;
        str += ",&nbsp;&nbsp;Price: " + r.getPrice();
        str += ",&nbsp;&nbsp;PeopleNum: " + r.getPeople();
        if (!r.getCusCode().equals("")) {
            str += ",&nbsp;&nbsp;Date: " + r.getrDate();
            Customer c = cusCont.Get(r.getCusCode());
            str += ",&nbsp;&nbsp;Customer Name: " + c.getCusFName() + " " + c.getCusLName();
            str += ",&nbsp;&nbsp;Customer Register: " + c.getCusReg();
            str += ",&nbsp;&nbsp;Customer Phone: " + c.getCusPhone();
        }
        return str;
    }

    public String GetCusElemWithAdmin(String num) {
        Customer c = cusCont.Get(num);
        String str = "<form th:action = \"@{/input}\", method='post'>";
        str += "<input name=\"button\" type=\"hidden\" value=" + "admin-deleteCust" + ">";
        str += "<input name=\"code\" type=\"hidden\" value=" + num + ">";
        str += "<input name=\"pass\" type=\"hidden\" value=\"back\">";
        str += "<input type=\"submit\" value = " + "Delete" + ">";
        str += "</form>";
        str += "&nbsp;&nbsp;CusCode: " + num;
        str += ",&nbsp;&nbsp;FName: " + c.getCusFName();
        str += ",&nbsp;&nbsp;LName: " + c.getCusLName();
        str += ",&nbsp;&nbsp;Phone: " + c.getCusPhone();
        str += ",&nbsp;&nbsp;Register: " + c.getCusReg();
        str += ",&nbsp;&nbsp;Birth: " + c.getCusBirth();
        return str;
    }

    public String GetRoomElem(String num) {
        Room r = roomCont.Get(Integer.parseInt(num));
        String str = "<form th:action = \"@{/input}\", method='post'>";
        String prog = "";
        System.out.println("\n\n\n" + Integer.parseInt(num) + "\n\n\n");
        if (r.getCusCode().equals(customer.getCusCode())) {
            prog = "Reject";
        } else {
            prog = "Order";
        }
        str += "<input name=\"button\" type=\"hidden\" value=" + "room" + ">";
        str += "<input name=\"code\" type=\"hidden\" value=" + num + ">";
        str += "<input name=\"pass\" type=\"hidden\" value=\"back\">";
        str += "<input type=\"submit\" value = " + prog + ">";
        str += "&nbsp;&nbsp;Name: " + num;
        str += ",&nbsp;&nbsp;Price: " + r.getPrice();
        str += ",&nbsp;&nbsp;PeopleNum: " + r.getPeople();
        
        if (!r.getCusCode().equals("")) {
            str += ",&nbsp;&nbsp;Date: " + r.getrDate();
            Customer c = cusCont.Get(r.getCusCode());
            str += ",&nbsp;&nbsp;Customer Name: " + c.getCusFName() + " " + c.getCusLName();
        }
        str += "</form>";
        return str;
    }

    public String GetCustomers() {
        List<Customer> cus = cusCont.GetAll();
        String str = "";
        str += "<tr>";
        str += "<td>" + "CusCode" + "</td>";
        str += "<td>" + "CusFName" + "</td>";
        str += "<td>" + "CusLName" + "</td>";
        str += "<td>" + "CusBirth" + "</td>";
        str += "<td>" + "CusReg" + "</td>";
        str += "<td>" + "CusPhone" + "</td>";
        str += "</tr>";
        for (int i = 0; i < cus.size(); i++) {
            if (cus.get(i).getCusCode().equals("")) {
                continue;
            }
            str += "<tr>";
            str += "<td>" + cus.get(i).getCusCode() + "</td>";
            str += "<td>" + cus.get(i).getCusFName() + "</td>";
            str += "<td>" + cus.get(i).getCusLName() + "</td>";
            str += "<td>" + cus.get(i).getCusBirth() + "</td>";
            str += "<td>" + cus.get(i).getCusReg() + "</td>";
            str += "<td>" + cus.get(i).getCusPhone() + "</td>";
            str += "</tr>";
        }
        return str;
    }

    public String GetRooms() {
        List<Room> cus = roomCont.GetAll();
        String str = "<table>";
        str += "<tr>";
        str += "<td>" + "Room id" + "</td>";
        str += "<td>" + "Is empty" + "</td>";
        str += "<td>" + "Cus code" + "</td>";
        str += "<td>" + "R date" + "</td>";
        str += "</tr>";
        for (int i = 0; i < cus.size(); i++) {
            str += "<tr>";
            str += "<td>" + cus.get(i).getRoomId() + "</td>";
            str += "<td>" + cus.get(i).getCusCode() + "</td>";
            str += "<td>" + cus.get(i).getrDate() + "</td>";
            str += "</tr>";
        }
        str += "</table>";
        return str;
    }
}
