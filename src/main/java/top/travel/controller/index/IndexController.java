package top.travel.controller.index;
import com.jfinal.core.Controller;

import top.travel.model.HotelImageModel;
import top.travel.model.HotelModel;
import top.travel.model.SightImageModel;
import top.travel.model.SightModel;
import top.travel.service.HotelService;
import top.travel.service.ImageService;
import top.travel.service.SightService;
import top.travel.util.MySession;

import java.util.ArrayList;
import java.util.List;

/**
 * IndexController 指向系统访问首页
 * @author jbolt.cn and Liao
 * @date 2019年3月1日
 */
public class IndexController extends Controller {
		/**
		 * 首页Action
		 *
		 */
		HotelService hotelService = new HotelService();
		SightService sightService = new SightService();
		ImageService imageService = new ImageService();
		public void index() {
			List<HotelModel> hotel_recommendations = hotelService.findAll();
			List<SightModel> sight_recommendations = sightService.findAll();
			List<HotelImageModel> hotel_images = new ArrayList<>();
			List<SightImageModel> sight_images = new ArrayList<>();;
			setAttr("hotel_recommendations",hotel_recommendations);
			setAttr("sight_recommendations",sight_recommendations);
			for(int i = 0;i < hotel_recommendations.size();i++)
			{
				int id = hotel_recommendations.get(i).getInt("h_id");
				HotelImageModel hotelImage = imageService.findAnHotelImage(id).get(0);
				hotel_images.add(hotelImage);
			}

			for(int i = 0;i <sight_recommendations.size();i++)
			{
				int id = sight_recommendations.get(i).getInt("s_id");
				SightImageModel sightImage = imageService.findAnSightImage(id).get(0);
				sight_images.add(sightImage);
			}
			setAttr("hotel_images",hotel_images);
			setAttr("sight_images",sight_images);
			render("/travelSite/index.html");
		}
}