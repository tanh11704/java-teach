package lab1.model;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SinhVienModel {
    private ArrayList<SinhVien> danhSachSinhVien;
    private final String FILE_NAME = "sinhvien.xml";
    
    public SinhVienModel() {
        danhSachSinhVien = new ArrayList<>();
        loadDataFromXML();
    }
    
    public ArrayList<SinhVien> getDanhSachSinhVien() {
        return danhSachSinhVien;
    }
    
    public boolean addSinhVien(SinhVien sv) {
        for (SinhVien existingSV : danhSachSinhVien) {
            if (existingSV.getStuID().equals(sv.getStuID())) { // Sinh Viên đã tồn tại
                return false;
            }
        }
        danhSachSinhVien.add(sv);
        saveDataToXML();
        return true;
    }
    
    public boolean updateSinhVien(SinhVien sv) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getStuID().equals(sv.getStuID())) {
                danhSachSinhVien.set(i, sv);
                saveDataToXML();
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteSinhVien(String stuID) {
        for (int i = 0; i < danhSachSinhVien.size(); i++) {
            if (danhSachSinhVien.get(i).getStuID().equals(stuID)) {
                danhSachSinhVien.remove(i);
                saveDataToXML();
                return true;
            }
        }
        return false;
    }
    
    public SinhVien findSinhVien(String stuID) {
        for (SinhVien sv : danhSachSinhVien) {
            if (sv.getStuID().equals(stuID)) {
                return sv;
            }
        }
        return null;
    }
    
    private void loadDataFromXML() {
        try {
            File file = new File(FILE_NAME);
            
            if (!file.exists()) {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                Element rootElement = doc.createElement("SinhViens");
                doc.appendChild(rootElement);
                
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(file);
                transformer.transform(source, result);
                return;
            }
            
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            
            NodeList nodeList = doc.getElementsByTagName("SinhVien");
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    
                    String id = element.getElementsByTagName("StuID").item(0).getTextContent();
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    String address = element.getElementsByTagName("Address").item(0).getTextContent();
                    
                    SinhVien sv = new SinhVien(id, name, address);
                    danhSachSinhVien.add(sv);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void saveDataToXML() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            
            Element rootElement = doc.createElement("SinhViens");
            doc.appendChild(rootElement);
            
            for (SinhVien sv : danhSachSinhVien) {
                Element sinhVienElement = doc.createElement("SinhVien");
                rootElement.appendChild(sinhVienElement);
                
                Element stuIDElement = doc.createElement("StuID");
                stuIDElement.appendChild(doc.createTextNode(sv.getStuID()));
                sinhVienElement.appendChild(stuIDElement);
                
                Element nameElement = doc.createElement("Name");
                nameElement.appendChild(doc.createTextNode(sv.getName()));
                sinhVienElement.appendChild(nameElement);
                
                Element addressElement = doc.createElement("Address");
                addressElement.appendChild(doc.createTextNode(sv.getAddress()));
                sinhVienElement.appendChild(addressElement);
            }
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(FILE_NAME));
            transformer.transform(source, result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
