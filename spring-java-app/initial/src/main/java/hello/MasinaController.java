package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;


@RestController
public class MasinaController {
  private List<Masina> masini = new ArrayList<Masina>();

  MasinaController() {
    Masina m1 = new Masina(1, "BMW", "M3");
    Masina m2 = new Masina(2, "Dacia", "Sandero");
    Masina m3 = new Masina(3, "Bugatti", "Veyron");

    masini.add(m1);
    masini.add(m2);
    masini.add(m3);
  }

  @RequestMapping(value="/masina", method = RequestMethod.GET)
  public List<Masina> index() {
    return this.masini;
  }

@RequestMapping(value="/masina", method = RequestMethod.POST)
  public ResponseEntity create(@RequestBody Masina m) {
	masini.add(m);
	
    return new ResponseEntity<Masina>(m, new HttpHeaders(), HttpStatus.OK);
  }


  @RequestMapping(value="/masina/{id}", method = RequestMethod.GET)
  public ResponseEntity get(@PathVariable("id") int id) {
    for(Masina m : this.masini) {
      if(m.getId() == id) {
        return new ResponseEntity<Masina>(m, new HttpHeaders(), HttpStatus.OK);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }


  @RequestMapping(value="/masina/{id}/{marca}/{model}", method = RequestMethod.PUT)
  public ResponseEntity put(@PathVariable("id") int id, @PathVariable("model") String model, @PathVariable("marca") String marca) {
  	for(Masina m : this.masini) {
      		if(m.getId() == id) {
			m.setId(id);
			m.setModel(model);
            m.setMarca(marca);
			return new ResponseEntity<Masina>(m, new HttpHeaders(), HttpStatus.OK);	
                }
  	}
	return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
    
    
  @RequestMapping(value="/masina/{id}", method = RequestMethod.DELETE)
  public ResponseEntity remove(@PathVariable("id") int id) {
    for(Masina m : this.masini) {
      if(m.getId() == id) {
        this.masini.remove(m);
        return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<String>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }
}

