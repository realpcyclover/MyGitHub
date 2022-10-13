package com.springmvc.demo.controllers;

import com.springmvc.demo.Core.*;
import com.springmvc.demo.models.*;
import com.springmvc.demo.repositories.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.RandomAccess;
import java.util.UUID;

@Controller
@RequestMapping(path = "AccountInfo")
public class AccountInfoController {

    @Autowired
    private AdressAccountRepository adressAccountRepository;
    @Autowired
    private AccountinfoRepository accountinfoRepository;
    @Autowired
    private CityAddressRepository cityAddressRepository;
    @Autowired
    private DistrictAdressRepository districtAdressRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private myinsuranceRepository myinsuranceRepository;
    @Autowired
    private  InsuranceCardRepository insuranceCardRepository;



//    http://localhost:8083/AccountInfo
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String GetIntoJSP(ModelMap modelMap,
                             @ModelAttribute("AccountAdress") AdressAccount adressAccount,
                             HttpServletRequest req,
                             HttpServletResponse res
    ) {
        if (req.getSession().getAttribute("Account") == null) {
            return "redirect:/Account";
        }
        Account crac = (Account) req.getSession().getAttribute("Account");
        modelMap.addAttribute("account",accountinfoRepository.findById(crac.getGmailAccount()).get());
        modelMap.addAttribute("acountinfo",accountinfoRepository.findById(crac.getGmailAccount()).get());
        modelMap.addAttribute("card",myinsuranceRepository.findAll());
//        myinsuranceRepository.findById().get().getIdmycard().replaceAll("-","").replaceAll("\\d", "asc");

        modelMap.addAttribute("reqlist",requestRepository.findAll());
        System.out.println();
        return "ProfileV2";

    }

    @RequestMapping(value = "/Delete/{adid}", method = RequestMethod.GET)
    public String DeleteAd(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @PathVariable int adid
    ) {
        adressAccountRepository.deleteById(adid);
        return "redirect:/AccountInfo";
    }

    public String updatead(AdressAccount adressAccount, int adid) {
        adressAccountRepository.findById(adid).get().setIDCityAddress(adressAccount.getIDCityAddress());
        adressAccountRepository.findById(adid).get().setIDDistrictAddress(adressAccount.getIDDistrictAddress());
        adressAccountRepository.findById(adid).get().setStreetAddress(adressAccount.getStreetAddress());
        adressAccountRepository.save( adressAccountRepository.findById(adid).get());
        return "redirect:/AccountInfo";
    }


    @RequestMapping(value = "Update", method = RequestMethod.POST)
    public String UpdateProfile(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @ModelAttribute("acountinfo") Accountinfo accountinfo
    ) {

        Account account = (Account) req.getSession().getAttribute("Account");
        Accountinfo acf = accountinfoRepository.findById(account.getGmailAccount()).get();
        acf.setFname(accountinfo.getFname());
        acf.setLname(accountinfo.getLname());
        acf.setPhoneNumber(accountinfo.getPhoneNumber());
        acf.setGender(accountinfo.getGender());
        accountinfoRepository.save(acf);
        return "redirect:/AccountInfo";


    }

    public  void GenarateDefaulAccount(String email
    ) {
        Accountinfo acf = new Accountinfo();
        acf.setGmailAccount(email);
        acf.setBalance(0);
        acf.setCreatedateaccount(new CurrentDate().GetDate());
        acf.setFname(RandomString.RandomStringN(5));
        acf.setLname(RandomString.RandomStringN(5));
        acf.setPhoneNumber(RandomNumber.RandNum(10));
        acf.setGender("male");
        acf.setImg(Base64.getDecoder().decode(new ImagesString().getDefaultAvatar()));
        System.err.println(acf.toString());
        System.err.println(accountinfoRepository.findAll().toString());
        accountinfoRepository.save(acf);
    }

    @RequestMapping(value = "Uploadimg", method = RequestMethod.POST)
    public String UpdateIMGProfile(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @ModelAttribute("acountinfo") Accountinfo accountinfo
    ) throws ServletException, IOException {
        System.err.println("Vao code update anh roi");
        if (req.getParameter("uploadimg") != null) {
            Part filePart = req.getPart("imgitem");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream fileContent = filePart.getInputStream();
//            byte[] img = IOUtils.readFully(fileContent, fileContent.available(), true);
            DataInputStream dataInputStr = new DataInputStream(fileContent);
            int count = dataInputStr.available();
            byte[] b = new byte[count];
            dataInputStr.readFully(b);

                if (fileName.isEmpty()) {
                    return "redirect:/AccountInfo";
                } else {
                    Account account = (Account) req.getSession().getAttribute("Account");
                    Accountinfo acf = accountinfoRepository.findById(account.getGmailAccount()).get();
                    acf.setImg(b);
                    accountinfoRepository.save(acf);
                    return "redirect:/AccountInfo";
                }

        }else{
            return "redirect:/AccountInfo";
        }
    }


    @RequestMapping(value = "uploadverify", method = RequestMethod.POST)
    public String VerifySend(ModelMap modelMap,
                                   HttpServletRequest req,
                                   HttpServletResponse res
    ) throws ServletException, IOException, ClassNotFoundException, SQLException {

        //File to byte[]
        Part filePart1 = req.getPart("img_item1");
        String fileName1 = Paths.get(filePart1.getSubmittedFileName()).getFileName().toString();
        if (fileName1.isEmpty()) {
            return "redirect:/AccountInfo";
        }
        Part filePart2 = req.getPart("img_item2");
        String fileName2 = Paths.get(filePart2.getSubmittedFileName()).getFileName().toString();
        if (fileName2.isEmpty()) {
            return "redirect:/AccountInfo";
        }

        InputStream fileContent1 = filePart1.getInputStream();
        DataInputStream dataInputStr1 = new DataInputStream(fileContent1);
        int count1 = dataInputStr1.available();
        byte[] b1 = new byte[count1];
        dataInputStr1.readFully(b1);

        InputStream fileContent2 = filePart2.getInputStream();
        DataInputStream dataInputStr2 = new DataInputStream(fileContent2);
        int count2 = dataInputStr2.available();
        byte[] b2 = new byte[count2];
        dataInputStr2.readFully(b2);

        ArrayList<byte[]> imgid = new ArrayList<>();
        imgid.add(b1);
        imgid.add(b2);

        byte[] mylistimg= ByteConvert.serialize(imgid);

        Account curentAccount=(Account)req.getSession().getAttribute("Account");
        Request newreq = new Request(UUID.randomUUID().toString(),curentAccount.getGmailAccount(),
                accountRepository.findById(curentAccount.getGmailAccount()).get().getTypeaccount(),
                1 ,
               "Verify_account",
               "SystemAuto",
               "Verify_account by"+curentAccount.getGmailAccount()+"date:"+new  CurrentDate().GetDate(),
                mylistimg,
                "SENT",
                new CurrentDate().GetDate());
        requestRepository.save(newreq);

       ArrayList<byte[]> list = (ArrayList<byte[]>)ByteConvert.deserialize(mylistimg);
       System.err.println("this í image aray: verify : "+list);

        return "redirect:/AccountInfo";
    }
    @RequestMapping(value = "showimages/{idreq}", method = RequestMethod.GET)
    public void TOImageRequestJSP(ModelMap modelMap,
                           HttpServletRequest req,
                           HttpServletResponse res,
                           @PathVariable String idreq
    ) throws IOException {
        ArrayList<String> listimg= requestRepository.findById(idreq).get().getImgString();
        res.getWriter().println("" +
                "<html style=\"height: 100%;\">\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, minimum-scale=0.1\">\n" +
                "    <title>View Image</title></head>\n" +
                "<body style=\"margin: 0px; background: #0e0e0e; height: 100%\">\n" +
                "<img\n" +
                "        height=\"353\"\n" +
                "        src=\""+listimg.get(0)+"\"\n" +
                "        style=\"display: block;-webkit-user-select: none;margin: auto;cursor: zoom-in;\n" +
                "        background-color: hsl(0, 0%, 90%);\n" +
                "        transition: background-color 300ms;\">\n" +
                "</body>\n" +
                "</html>");
        res.getWriter().println("" +
                "<html style=\"height: 100%;\">\n" +
                "<head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, minimum-scale=0.1\">\n" +
                "    <title>View Image</title></head>\n" +
                "<body style=\"margin: 0px; background: #0e0e0e; height: 100%\">\n" +
                "<img\n" +
                "        height=\"353\"\n" +
                "        src=\""+listimg.get(1)+"\"\n" +
                "        style=\"display: block;-webkit-user-select: none;margin: auto;cursor: zoom-in;\n" +
                "        background-color: hsl(0, 0%, 90%);\n" +
                "        transition: background-color 300ms;\">\n" +
                "</body>\n" +
                "</html>");

//        return "/paypal_views/paypal";
    }

    @RequestMapping(value = "accptver/{idreqgmail}/{acp}", method = RequestMethod.GET)
    public String AccpetVerify(ModelMap modelMap,
                                  HttpServletRequest req,
                                  HttpServletResponse res,
                                  @PathVariable String idreqgmail,
                               @PathVariable String acp
    ) throws IOException {
                Accountinfo acre = accountinfoRepository.findById(idreqgmail).get();
                if(acp.equals("accept")){
                    acre.setVerify("verified");
                    accountinfoRepository.save(acre);
                }else{
                    acre.setVerify("verified");
                    accountinfoRepository.save(acre);
                }
        return "redirect:/AdminAccount ";
    }

    @RequestMapping(value = "addreport", method = RequestMethod.POST)
    public String addreport(ModelMap modelMap,
                               HttpServletRequest req,
                               HttpServletResponse res
    ) throws IOException, ServletException, SQLException {
        Account curentAccount=(Account)req.getSession().getAttribute("Account");
        Part filePart = req.getPart("docitem");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        InputStream fileContent = filePart.getInputStream();
        DataInputStream dataInputStr = new DataInputStream(fileContent);
        int count = dataInputStr.available();
        byte[] docx = new byte[count];
        dataInputStr.readFully(docx);
        req.getSession().setAttribute("doc",docx);
        System.out.println("Du lieu file docx:"+docx.toString());
        //Get input
        System.out.println("Du lieu Title :"+req.getParameter("title"));
        System.out.println("Du lieu Title :"+req.getParameter("content"));
        //Get String type file
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        //
        Request newreq = new Request(UUID.randomUUID().toString(),curentAccount.getGmailAccount(),
                accountRepository.findById(curentAccount.getGmailAccount()).get().getTypeaccount(),
                1  ,
                "Report",
                req.getParameter("title"),
                req.getParameter("content")+"SYTEMCONTENTFILEATTACHMENTTYPE:"+extension+"",
                docx,
                "In-Line",
                new CurrentDate().GetDate());

        requestRepository.save(newreq);

        return "redirect:/AccountInfo ";
    }

    @RequestMapping(value = "getfile/{idreq}", method = RequestMethod.GET)
    public @ResponseBody void Dowfile(ModelMap modelMap,
                                    HttpServletRequest req,
                                    HttpServletResponse res,
                                    @PathVariable String idreq
    ) throws ServletException, IOException {
        String filetype=requestRepository.findById(idreq).get().getContentRequest().split("SYTEMCONTENTFILEATTACHMENTTYPE:")[1];
        byte[] rawFile = (byte[])req.getSession().getAttribute("doc");
        res.setContentType("application/"+filetype+"");
        res.setHeader("Content-Disposition", "attachment;filename=testing."+filetype+"");
        OutputStream out = res.getOutputStream();
        out.write(rawFile);

//        return rawFile;
    }


    @RequestMapping(value = "pending/{type}", method = RequestMethod.GET)
    public String TOPAYPAL(ModelMap modelMap,
                                HttpServletRequest req,
                                HttpServletResponse res,
                                @ModelAttribute("acountinfo") Accountinfo accountinfo,
                                @PathVariable String type
    ) {

        modelMap.addAttribute("typepay",type);
        return "/paypal_views/paypal";


    }

    @RequestMapping(value = "SingleInsurance", method = RequestMethod.GET)
    public String Tosingleinsurance(ModelMap modelMap,
                           HttpServletRequest req,
                           HttpServletResponse res
    ) {
        return "/SingleInsurance";
    }

    @RequestMapping(value = "reqcard", method = RequestMethod.POST)
    public String RequestNewCard(ModelMap modelMap,
                                    HttpServletRequest req,
                                    HttpServletResponse res
    ) {
        Account curentAccount=(Account)req.getSession().getAttribute("Account");
        Accountinfo accountinfo = accountinfoRepository.findById(curentAccount.getGmailAccount()).get();
        myinsuranceRepository.save( new myinsurance( UUID.randomUUID().toString(),curentAccount.getGmailAccount(),
                req.getParameter("idcard"),  new CurrentDate().GetDate(),
                new CurrentDate().GetDateAfterDays(365*Integer.valueOf(req.getParameter("year"))),
                "true"));
        accountinfo.setBalance(accountinfo.getBalance()- insuranceCardRepository.findById(req.getParameter("idcard")).get().getPriceByYear());
        accountinfoRepository.save(accountinfo);
        System.err.println(accountinfoRepository.findById(curentAccount.getGmailAccount()).get().getAccount().getMyinsurances().toString());
        return "redirect:/home";
    }

}
