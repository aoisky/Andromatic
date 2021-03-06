package io.github.fleetc0m.andromatic.trigger;

import io.github.fleetc0m.andromatic.R;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class DrivingModeTrigger extends Trigger{

	private EditText speedEdit;
	private float curr_speed;
	LocationManager locationManager; 
	LocationListener locationListener = new LocationListener(){
		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
			curr_speed = (float) (location.getSpeed()*2.2369);
		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status,
				Bundle extras) {
		}
	};
	
	public DrivingModeTrigger(){
		super(null);
	}
	
	public DrivingModeTrigger(Context context){
		super(context);
		locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	}

	@Override
	public void setContext(Context context){
		super.setContext(context);
		locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	@Override
	public View getConfigView(String savedRule) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = (LayoutInflater) 
				context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.driving_mode_trigger, null);
		speedEdit = (EditText) view.findViewById(R.id.driving_mode_trigger_prompt_speed_edit);
		String speed = savedRule;
		if(speed != null){
			speedEdit.setText(speed);
		}
		return view;
	}

	@Override
	public View getConfigView() {
		// TODO Auto-generated method stub
		return getConfigView(null);
	}

	@Override
	public boolean trig() {
		return curr_speed>=Float.parseFloat(savedRule);
	}

	@Override
	public String getConfigString() {
		if(speedEdit.getText().length()==0){
			savedRule = ""+40;
		}else{
			savedRule = speedEdit.getText().toString();
		}
		return savedRule;
	}

	@Override
	public String getIntentAction() {
		return null;
	}

	@Override
	public String getHumanReadableString() {
		return getHumanReadableString(savedRule);
	}

	@Override
	public String getHumanReadableString(String rule) {
		// TODO Auto-generated method stub
		return "When your speed exceeds "+rule;
	}

	@Override
	public boolean needPolling() {
		return true;
	}

}
