package com.rocketseat.planner.activity;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rocketseat.planner.participant.ParticipantData;
import com.rocketseat.planner.trip.Trip;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository repository;

	public ActivityResponse registeActivity(ActivityRequestPayload payLoad, Trip trip) {
		Activity newActivity = new Activity(payLoad.title(), payLoad.occurs_at(), trip);

		this.repository.save(newActivity);

		return new ActivityResponse(newActivity.getId());
	}

	public List<ActivityData> getAllActivitiesFromId(UUID tripId) {
		return this.repository.findByTripId(tripId).stream()
				.map(activity -> new ActivityData(activity.getId(), activity.getTitle(), activity.getOccursAt()))
				.toList();
	}
}
